import axios from 'axios';

export function getAxios(token){
    if(token != null) {
        return axios.create({
            baseURL: "/",
            headers: {
                Authorization: 'Bearer ' + token
            }
        });
    }
    else {
        return axios.create({
            baseURL: "/"
        })
    }
}


