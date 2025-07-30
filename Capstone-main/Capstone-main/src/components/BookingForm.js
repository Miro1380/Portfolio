
import {useState, useEffect} from "react";
import { useNavigate } from "react-router-dom";
import { fetchAPI,submitAPI } from "../timeAPI";

const BookingForm = ({timeOptions,setTimeOptions}) => {

    const navigate = useNavigate();
    const [validated, setValidated] = useState({valid:false});

    const [validForm, setValidForm] = useState({
         date:false,
         availableTime:false,
         number:false,
         occasion:false,
        });

    //State vars for Date,Time, number, and occassion
    const [form,setForm] = useState({
        date: '',
        availableTime:'',
        number: 0,
        occasion:'',
    });


    const handleBlur = (e) => {
        let field = e.target.name;
        let value = e.target.value;

        if(value !== ""){
            setValidForm({...validForm,[field]:true});
        }
        console.log("Current State: ", validForm);
        if( (validForm.date === true && validForm.availableTime)
            && (validForm.number === true && validForm.occasion === true)){
                setValidated({valid:true})
        }
    }

//onChange. Copies form and changes affected field.
    const handleChange = (e) => {
        if(e.target.value !== "") {
            //Set form here
            console.log("HANDLER TEST")
        }else{
            console.log("ELSE HANDLER")
            return <div> Please select a valid value </div>
        }
        setForm({
            ...form,
            [e.target.name]:e.target.value,
        })
    }

     //API DATA
     useEffect(() => {
        const testDate = new Date();
        //console.log("Today's DATE:" , testDate);
        let times = fetchAPI(testDate);
        //console.log(times);
        setTimeOptions(times);
    }, [form,validated,validForm,setTimeOptions,setForm,setValidated,setValidForm]);




//Check form after submit and remove selected time from available times.
const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Submitted form: ",form);
    let valid = false;
    const newTimes = [];
    timeOptions.filter((item) => {
        if(item.value !== form.availableTime){
               newTimes.push(item);
            }
        return newTimes
        })
    setTimeOptions(newTimes);

        //Check the fields are not null,empty or undefined
        for(let key in form){
            console.log("KEY: "+ key);
            let val = form[key];
            console.log("VALS: " + val);
            if(val !== null && val !== '' && val !== undefined){
                valid = true;
            }
        }
        console.log("Checking form...")

        //Reset form
        setForm({
            date:'',
            availableTime:'',
            number:0,
            occasion:'Birthday'});

            //console.log("FORM RESET:" + form.date);


        //If no field in form is null consider valid
        if(valid === true){
            console.log("Valid Form");
            console.log("Submit Result: "+ submitAPI(form));
            valid=false;
            navigate("/confirmed")
        }else if(valid === false){
            console.log("Form Invalid");
        }
        valid = false;
        console.log("AFTER:", form.date,form.availableTime);
}
    return (
        <>
            <form className="form" onSubmit={handleSubmit}>
                <label aria-label="Choose a date" htmlFor="res-date">Choose date</label>
                <input
                    type="date"
                    name="date"
                    id="res-date"
                    placeholder="Choose a date"
                    required
                    onBlur = {handleBlur}
                    value={form.date}
                    onChange={handleChange}/>
                <label aria-label="Choose a time" htmlFor="res-time">Choose time</label>
                <select
                    type="time"
                    id="res-time "
                    name="availableTime"
                    placeholder="Choose a Time"
                    required
                    value={form.availableTime}
                    onBlur={handleBlur}
                    onChange={handleChange}
                >
                <option value=""> Please select a Time</option>
                 {timeOptions.map((item) => <option key={item.id} value = {item.value}> {item.label} </option>)}
                </select>
                <label aria-label="Number of Guests" htmlFor="guests" data-test-id="label">Number of guests</label>
                <input
                    data-test-id="increment"
                    type="number"
                    placeholder="1"
                    min="1" max="10"
                    name="number"
                    id="guests"
                    value={form.number}
                    onBlur={handleBlur}
                    onChange={ handleChange}/>
                <label aria-label="Select an occasion" htmlFor="occasion">Occasion</label>
                <select
                    required
                    id="occasion"
                    name="occasion"
                    value={form.occasion}
                    onChange={handleChange}
                    onBlur={handleBlur}>
                    <option value=""> Please select an Occassion</option>
                    <option value="Birthday">Birthday</option>
                    <option value="Anniversary">Anniversary</option>
                </select>
                <input id="frm-bttn" type="submit" value="Make Your reservation" disabled={!validated.valid}/>
            </form>
        </>
    )
}

export default BookingForm;