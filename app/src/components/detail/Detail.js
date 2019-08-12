import React, { Component } from "react";
import { Button, Spinner } from "reactstrap";
import { Link } from "react-router-dom";
import "./Detail.css";

class Detail extends Component {
  constructor(props) {
    super(props);
    this.state = {
      order: [],
      product: {},
      goal: {},
      category: {},
      reviews: [],
      isLoading: true
    };
  }

  componentDidMount() {
    this.setState({
      isLoading: true
    });

    fetch(`/api/products/${this.props.match.params.id}`)
      .then(res => res.json())
      .then(data => {
        this.setState({
          product: data,
          goal: data.goal,
          category: data.category
        });
      });

    fetch(`/api/reviews/product/${this.props.match.params.id}`)
      .then(res => res.json())
      .then(data => {
        this.setState({
          reviews: data,
          isLoading: false
        });
      });
  }

  addToOrder(product) {
    localStorage.setItem(product.productId, JSON.stringify(product));
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
    const { product, reviews, goal, category, isLoading } = this.state;

    if (isLoading) {
      return (
        <div className="details mt-5">
          <div className="container">
            <Spinner style={{ width: "6rem", height: "6rem" }} type="grow" />;
          </div>
        </div>
      );
    }

    return (
      <div className="details mt-5">
        <div className="container">
          <Button tag={Link} to="/products">
            Back To Products
          </Button>
          <div className="row mt-4">
            <div className="col-md-4">
              <img src={product.image} alt={product.image} className="mb-3" />
              <h5>Product Overview</h5>
              <p>
                Lorem ipsum dolor, sit amet consectetur adipisicing elit.
                Repudiandae nisi accusamus pariatur dicta adipisci saepe atque
                necessitatibus molestiae ratione asperiores minima ducimus in
                corrupti tempora nostrum possimus consectetur, vero corporis.
              </p>
            </div>
            <div className="col-md-8">
              <div className="row">
                {" "}
                <h2 className="ml-3">{product.name}</h2>
              </div>
              <div className="row">
                <p className="ml-3">{product.headline}</p>
              </div>
              <hr />
              <div className="row">
                <h5 className="ml-3">${product.price}</h5>
              </div>
              <hr />
              <div className="row">
                <div className="col">
                  <Button outline color="success" disabled>
                    {goal.name}
                  </Button>{" "}
                </div>
                <div className="col">
                  <Button outline color="info" disabled>
                    {category.name}
                  </Button>{" "}
                </div>
              </div>
              <div className="row mt-5">
                <div className="col">
                  {localStorage.getItem(product.productId) ? (
                    <Button
                      outline
                      color="warning"
                      onClick={this.deleteFromOrder.bind(this, product)}
                      size="lg"
                      block
                    >
                      Remove From Order
                    </Button>
                  ) : (
                    <Button
                      outline
                      color="primary"
                      onClick={this.addToOrder.bind(this, product)}
                      size="lg"
                      block
                    >
                      Add To Order
                    </Button>
                  )}
                </div>
              </div>
            </div>
          </div>
          <hr />
          <div className="row mt-4">
            <div className="col">
              <h3>Reviews</h3>
            </div>
            <div className="col-right">
              <Button
                outline
                color="warning"
                tag={Link}
                to={`/add/review/${product.productId}`}
              >
                Add Review
              </Button>
            </div>
          </div>
          {reviews.map(r => (
            <div key={r.id}>
              <div className="row mt-3 ml-2">
                <h5>{r.rating} Stars!</h5>
              </div>
              <div className="row mt-3 ml-2">
                <p>{r.description}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }
}

export default Detail;
