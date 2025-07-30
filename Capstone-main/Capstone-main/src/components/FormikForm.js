import {useFormik} from 'formik'
import * as Yup from 'yup';

const FormikForm = () => {

    const times = ["Please select a time","10:00","10:30","11:00", "11:30", "12:00", "12:30","1:00"];

    const onSubmit = async(values,actions) => {
        console.log("Reservation submitted")
        console.log("Vals: ", values);
        console.log("Act: " , actions);
        await new Promise((resolve) => setTimeout(resolve,2000));
        actions.resetForm();
    }

    const FormSchema = Yup.object().shape({
        name:Yup.string().min(2,"Name must be at least 2 characters").required("Name is required"),
        email:Yup.string().email('Invalid Email').required("Email is Required"),
        date: Yup.string().required("Please select a date"),
        times:Yup.string().oneOf(times),
        size: Yup.number().positive().min(1,"Party size must be greater than 0").max(8,"Contact us for party's greater than 8").required("Party size is required")
    })

    //Some guides suggets destructuring formik. ex: {value,handleBlur,onChange} = useFormik({})
    const formik = useFormik({
        initialValues:{
            name:'',
            email: '',
            date: '',
            time:times,
            size:'',
        },
        validationSchema:FormSchema,
        onSubmit,
    });
    console.log("Errors:", formik.errors);
    return (
        <>
            <form className="form" onSubmit={formik.handleSubmit}>

                <label>Enter Name: </label>
                <input
                    type="text"
                    id="name"
                    name="name"
                    onChange={formik.handleChange}
                    value={formik.values.name}
                    onBlur={formik.handleBlur}
                    className={formik.errors.name && formik.touched.name? "input-error": ""}>
                </input>
                {formik.errors.name && formik.touched.name? <p className="error"> {formik.errors.name} </p>: ""}


                <label>Enter Email: </label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    onChange={formik.handleChange}
                    value={formik.values.email}
                    onBlur={formik.handleBlur}
                    className={formik.errors.email && formik.touched.email? "input-error" : ""}>
                </input>
                {formik.errors.email && formik.touched.email? <p className="error"> {formik.errors.email} </p>: ""}


                <label>Choose Date</label>
                <input
                    type="date"
                    id="date"
                    name="date"
                    onChange={formik.handleChange}
                    value={formik.values.date}
                    onBlur={formik.handleBlur}
                    className={formik.errors.date && formik.touched.date? "input-error" : ""}>
                </input>
                {formik.errors.date && formik.touched.date? <p className="error">{ formik.errors.date } </p>: ""}

                <label> Choose Time </label>
                <select
                    type="text"
                    id="times"
                    name="times"
                    onChange={formik.handleChange}
                    value={formik.times}
                    onBlur={formik.handleBlur}
                    className={formik.errors.times && formik.touched.times? "input-error" : ""}>
                    {times.map((time)=> <option key={time} value={time}> {time} </option>)}
                </select>
                {formik.errors.times && formik.touched.times? <p className="error"> {formik.errors.times} </p> : ""}

                <label>Party Size</label>
                <input
                    type="number"
                    id="size"
                    name="size"
                    onChange={formik.handleChange}
                    value={formik.values.size}
                    onBlur={formik.handleBlur}
                    className={formik.errors.size && formik.touched.size? "input-error": ""}>
                </input>
                    {formik.errors.size && formik.touched.size? <p className="error"> {formik.errors.size} </p> : ""}

                <button type="submit" id="frm-bttn" disabled={formik.isSubmitting}> Make Reservation </button>

            </form>
        </>
    )
}

export default FormikForm;