import axios from 'axios';
import { store } from '../main';

const http = axios.create({
    baseURL: 'http://localhost:8080/api/department'
});

export default {
    listDepartments() {
        return http.get('', { headers: { Authorization: `Bearer ${store.state.token}` } });
    },
    getDepartmentById(id) {
        return http.get(`/${id}`, { headers: { Authorization: `Bearer ${store.state.token}` } });
    }
}