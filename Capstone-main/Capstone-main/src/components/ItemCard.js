const ItemCard = ({item}) => {

    return(
        //TODO fix CSS for CARD ITEM
        //Image
        //Title and price centered
        //Paragraph with description
        //Order / add to cart? button
        <div className="card">
            <img src={item.location} alt=""/>
            <div className="cardInfo">
                <div className="cardItemTitleAndPrice cardItemColSize">
                    <div className="title">{item.title}</div>
                    <div className="price">${item.price}</div>
                </div>
                <div className="cardItemDescription cardItemColSize">
                    <p> {item.desc}</p>
                </div>
                <div className="cardItemButton cardItemColSize">
                    <button> Order a delivery</button>
                </div>
            </div>
        </div>
    )
}

export default ItemCard;