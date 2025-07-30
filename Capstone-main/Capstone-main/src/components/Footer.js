import {Link} from "react-router-dom";

const Footer = () => {
    return (
        <>
            <footer className="footer">
                <ul>
                    <li><Link  to="#contact"> Contact info </Link></li>
                    <li><Link  to="#social"> Link Social media</Link></li>
                </ul>
            </footer>
        </>
    )
}

export default Footer;