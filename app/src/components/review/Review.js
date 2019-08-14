import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import {
  Alert,
  Button,
  Form,
  FormGroup,
  Label,
  Input,
  Spinner
} from "reactstrap";
import axios from "axios";
import "./Review.css";

class Review extends Component {
  _isMounted = false;

  constructor(props) {
    super(props);
    this.state = {
      product: {},
      review: {},
      rating: "",
      productId: "",
      description: "",
      isLoading: true,
      visible: false,
      errorMsg: ""
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.onDismiss = this.onDismiss.bind(this);
  }

  componentDidMount() {
    this._isMounted = true;

    this.setState({
      isLoading: true
    });

    axios
      .get(`/api/products/${this.props.match.params.id}`)
      .then(response => {
        if (this._isMounted) {
          this.setState({
            product: response.data,
            isLoading: false
          });
        }
      })
      .catch(error => {
        console.log(error);
      });
  }

  componentWillUnmount() {
    this._isMounted = false;
  }

  handleChange = event => {
    const { name, value } = event.target;
    this.setState({
      [name]: value
    });
  };

  async handleSubmit(event) {
    event.preventDefault();
    const { rating, description, product } = this.state;

    const reviewData = {
      productId: product.productId,
      rating: rating,
      description: description
    };

    // need better validation
    if (rating <= 5) {
      axios
        .post("/api/reviews", reviewData)
        .then(res => {
          this.props.history.push(`/products/${this.props.match.params.id}`);
        })
        .catch(error => {
          console.log(error);
        });
    } else {
      const { rating } = this.state;

      this.setState({
        visible: true,
        errorMsg: `A Rating of ${rating} is great! But ratings cannot be higher than 5.`
      });
    }
  }

  onDismiss() {
    this.setState({ visible: false });
  }

  render() {
    const { product, isLoading } = this.state;

    if (isLoading) {
      return (
        <div className="review mt-5">
          <div className="container">
            <Spinner style={{ width: "6rem", height: "6rem" }} type="grow" />;
          </div>
        </div>
      );
    }

    return (
      <div className="review mt-5">
        <div className="container">
          <Button tag={Link} to={`/products/${product.productId}`}>
            Back To {product.name}
          </Button>
          <div className="row mt-4">
            <div className="col-md-4">
              <img src={product.image} alt={product.image} className="mb-3" />
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
                <h4 className="ml-3">Add A Review</h4>
              </div>
              <div className="row">
                <div className="col">
                  <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                      <Label for="rating">Rating (1 - 5)</Label>
                      <Input
                        type="number"
                        name="rating"
                        id="rating"
                        onChange={this.handleChange}
                        value={this.state.rating}
                      />
                    </FormGroup>
                    <Alert
                      color="danger"
                      isOpen={this.state.visible}
                      toggle={this.onDismiss}
                      className="mt-3"
                    >
                      {this.state.errorMsg}
                    </Alert>
                    <FormGroup>
                      <Label for="description">What do you have to say?</Label>
                      <Input
                        type="textarea"
                        name="description"
                        id="description"
                        onChange={this.handleChange}
                        value={this.state.description}
                      />
                    </FormGroup>
                    <FormGroup>
                      <Button
                        outline
                        color="primary"
                        type="submit"
                        // need better validation
                        disabled={!this.state.rating}
                      >
                        Save
                      </Button>
                      <Button
                        tag={Link}
                        to={`/products/${product.productId}`}
                        className="ml-2"
                      >
                        Cancel
                      </Button>
                    </FormGroup>
                  </Form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default withRouter(Review);
