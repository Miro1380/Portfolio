import Img1 from "../icons_assets/restaurant.jpg"
import Img2 from "../icons_assets/Mario&AdrianA.jpg"

const AboutUs = () => {
    return(
    <>
        <div id="about">
            <h1> About Us</h1>
        </div>
        <div id="abtImg">
            <img display="block" width="1000px" height="500px" src={Img1}/>
        </div>
        <div id="abtP">
            <h1 id="abth3">Who are we?</h1>
            <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <div id="abtImg">
                <img width="1000" height="500" src={Img2}/>
            </div>
        </div>
    </>
    );
}

export default AboutUs;