
import Img1 from "../icons_assets/restauranfood.jpg"
import {Link} from 'react-router-dom';

const Header = () => {
    return (
    <div className="header">
            <div id="banner">
                <div>
                <h1 > Little Lemon</h1>
                <h2> Chicago </h2>
                    <p> We are a family owned mediterrannean restaurant,
                        focused on traditional served with a modern twist.
                    </p>
                    <Link to="/booking"> <button>Reserve a table </button> </Link>
                </div>
                <div>
                <img alt="" src= {Img1}/>
                </div>
            </div>
    </div>
    )
}

export default Header;