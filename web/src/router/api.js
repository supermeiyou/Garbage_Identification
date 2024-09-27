import axios from "axios";

export default {
    getPicture(){
        return axios.get('http://localhost:8080/file/get');
    },
};
