import React, { Component } from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import { Card, CardImg, CardBody, CardText, CardTitle } from "reactstrap";
import "./ProductCard.css";

class ProductCard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      order: []
    };
  }

  addToOrder(product) {
    localStorage.setItem(product.productId, product);
    window.location.reload();
    // this.setState((prevState, props) => {
    //   return {
    //     order: [...prevState.order, product]
    //   };
    // });
  }

  deleteFromOrder(product) {
    localStorage.removeItem(product.productId);
    window.location.reload();
    // this.setState({
    //   order: this.state.order.filter(prod => {
    //     return prod !== product.target.value;
    //   })
    // });
  }

  render() {
    const { product } = this.props;

    return (
      <div>
        <Card>
          <Link to={`/products/${product.productId}`}>
            <CardImg
              top
              width="100%"
              src={product.image}
              alt={product.image}
              className="productImage"
            />
          </Link>
          <CardBody>
            <CardTitle>{product.name}</CardTitle>
            <CardText>${product.price}</CardText>
            {/* {this.state.order.includes(product) ? ( */}
            {/* {localStorage.getItem(product.productId) ? (
              <Button
                outline
                color="warning"
                onClick={this.deleteFromOrder.bind(this, product)}
              >
                Remove From Order
              </Button>
            ) : (
              <Button
                outline
                color="primary"
                onClick={this.addToOrder.bind(this, product)}
              >
                Add To Order
              </Button>
            )} */}
          </CardBody>
        </Card>
      </div>
    );
  }
}

ProductCard.propTypes = {
  product: PropTypes.object.isRequired
};

export default ProductCard;
