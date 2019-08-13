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
