import Logo from "../icons_assets/Logo.svg"
import {Link} from 'react-router-dom';

const Nav = () => {
    return(
        <nav className="navbar">
            <img src= {Logo} alt="logo"></img>
            <ul>
                <li><Link to="/"> Home </Link></li>
                <li><Link to="/about"> About </Link></li>
                <li><Link to="/booking"> Reservations </Link></li>
                <li><Link to="/order"> Order Online </Link></li>
                <li><Link to="/login"> Login </Link></li>
            </ul>
        </nav>
    )
}
export default Nav;