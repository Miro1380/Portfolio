import ItemCard from "./ItemCard"
import Img1 from "../icons_assets/greekSalad.jpg"
import Img2 from "../icons_assets/lemonDessert.jpg"
import Img3 from "../icons_assets/bruchetta.svg"

const Item1 = {
    location: Img1,
    title:"Greek Salad",
    price: 12.99,
    desc:"The famous greek salad of crispy lettuce, peppers, olives.",
}
const Item2 = {
    location: Img2,
    title:"Lemon Dessert",
    price: 15.99,
    desc:"This comes straight from grandma's recipe book every last ingredient has been sourced and is as authentic as can be imagined.",
}

const Item3 = {
    location: Img3,
    title:"Bruchetta",
    price: 17.99,
    desc:"Our bruchetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil.",
}

const Specials = () => {
    return (
        <div className="mainContainer">
        <div className="main">
            <h1> This week's specials!</h1>
            <button> Online Menu </button>
        </div>
        <div className="cardItems">
            <ItemCard item={Item1}/>
            <ItemCard item={Item2}/>
            <ItemCard item={Item3}/>
        </div>
        </div>
    )
}

export default Specials;