import React, { Component } from "react";
import { Button, Form, FormGroup, Label, Input } from "reactstrap";
import "./Goal.css";

class Goal extends Component {
  constructor(props) {
    super(props);
    this.state = {
      goals: [],
      name: "",
      isLoading: true
    };
    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {
    this.setState({
      isLoading: true
    });

    fetch("/api/goals")
      .then(res => res.json())
      .then(data =>
        this.setState({
          goals: data,
          isLoading: false
        })
      );
  }

  handleInputChange = event => {
    const { name, value } = event.target;
    this.setState({
      [name]: value
    });
  };

  async handleSubmit(event) {
    event.preventDefault();
    const { name } = this.state;

    console.log(JSON.stringify(name));

    await fetch("/api/goals", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(name)
    });
    this.props.history.push("/goals");
  }

  render() {
    const { goals, isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div className="goals mt-5">
        <div className="container">
          <h1>Goals</h1>
          <div className="row mt-4">
            {goals.map(goal => (
              <div className="col-md-3" key={goal.id}>
                <Button className="ml-3 mt-5">{goal.name}</Button>
              </div>
            ))}
          </div>
          <div className="row mt-5">
            <h5 className="ml-3 mt-5">Add A Goal</h5>
          </div>
          <div className="row">
            <div className="col-md-3">
              <Form onSubmit={this.handleSubmit}>
                <FormGroup>
                  <Label for="name">Name</Label>
                  <Input
                    type="text"
                    id="name"
                    name="name"
                    value={this.state.name}
                    onChange={this.handleInputChange}
                  />
                </FormGroup>
                <FormGroup>
                  <Button
                    outline
                    color="primary"
                    type="submit"
                    disabled={!this.state.name}
                  >
                    Save
                  </Button>
                </FormGroup>
              </Form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Goal;
