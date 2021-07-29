import axios from "axios";
const API_URL = "http://localhost:8080/users";

const register = (user) => {
  return axios
    .post(API_URL, {
      user
    })
    .then((response) => {
      return response.data;
    });
};

const login = (username) => {
  return axios
    .post(API_URL + "/login", {
      username
    })
    .then((response) => {
      return response.data;
    });
};

export default {
  login
};
