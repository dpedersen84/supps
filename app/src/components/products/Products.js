import React, { Component } from "react";
import { Button, Spinner } from "reactstrap";
import ProductCard from "../product-card/ProductCard";
import "./Products.css";

class Products extends Component {
  _isMounted = false;

  constructor(props) {
    super(props);
    this.state = {
      products: [],
      goals: [],
      categories: [],
      isLoading: true
    };
  }

  componentDidMount() {
    this._isMounted = true;

    this.setState({
      isLoading: true
    });

    fetch("/api/goals")
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            goals: data
          });
        }
      });

    fetch("/api/category")
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            categories: data
          });
        }
      });

    fetch("/api/products")
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            products: data,
            isLoading: false
          });
        }
      });
  }

  componentWillUnmount() {
    this._isMounted = false;
  }

  handleGoalClick(goalId) {
    this.setState({
      isLoading: true
    });

    fetch(`api/products/goal/${goalId}`)
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            products: data,
            isLoading: false
          });
        }
      });
  }

  handleCategoryClick(categoryId) {
    this.setState({
      isLoading: true
    });

    fetch(`api/products/category/${categoryId}`)
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            products: data,
            isLoading: false
          });
        }
      });
  }

  handleAllProductsClick() {
    this.setState({
      isLoading: true
    });

    fetch("/api/products")
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            products: data,
            isLoading: false
          });
        }
      });
  }

  render() {
    const { products, goals, categories, isLoading } = this.state;

    if (isLoading) {
      return (
        <div className="products mt-5">
          <div className="container">
            <Spinner style={{ width: "6rem", height: "6rem" }} type="grow" />;
          </div>
        </div>
      );
    }

    return (
      <div className="products mt-5">
        <div className="container">
          <h1>Products</h1>
          <div className="row mt-1">
            {goals.map(goal => (
              <div className="col-md-2" key={goal.id}>
                <Button
                  onClick={() => this.handleGoalClick(goal.id)}
                  outline
                  color="success"
                  className="ml-1 mt-1"
                >
                  {goal.name}
                </Button>
              </div>
            ))}
          </div>
          <div className="row mt-1">
            {categories.map(category => (
              <div className="col-md-2" key={category.id}>
                <Button
                  onClick={() => this.handleCategoryClick(category.id)}
                  outline
                  color="info"
                  className="ml-1 mt-1"
                >
                  {category.name}
                </Button>
              </div>
            ))}
          </div>
          <div className="row mt-1">
            <div className="col-md-2">
              <Button
                onClick={() => this.handleAllProductsClick()}
                outline
                color="primary"
                className="ml-1 mt-1"
              >
                All Products
              </Button>
            </div>
          </div>
          <div className="row mt-4">
            {products.map(product => (
              <div className="col-md-3 mb-4" key={product.productId}>
                <ProductCard product={product} />
              </div>
            ))}
          </div>
        </div>
      </div>
    );
  }
}

export default Products;
