import Nav from "../components/Nav";
import FormikForm from "../components/FormikForm";
import Footer from "../components/Footer";
import {useState} from "react";



const BookingPage = () => {
    const [timeOptions,setTimeOptions] = useState([]);

    return(
        <>
        <Nav/>
        <FormikForm></FormikForm>
        <Footer/>
        </>
    )
}

export default BookingPage;