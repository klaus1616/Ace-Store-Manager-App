import axios from "axios"
import { store } from '../main';

const http = axios.create({
    baseURL: "http://localhost:8080/api/employee"
});


export default {
    listEmployees() {
        return http.get('', { headers: { Authorization: `Bearer ${store.state.token}` } });
    },
    getEmployee(id) {
        return http.get('/' + id, { headers: { Authorization: `Bearer ${store.state.token}` } });
    },
    addEmployee(employee) {
        return http.post("", employee, { headers: { Authorization: `Bearer ${store.state.token}` } });
    },
    updateEmployee(id, employee) {
        return http.put(`/${id}`, employee, { headers: { Authorization: `Bearer ${store.state.token}` } });
    },
    deleteEmployee(id) {
        return http.delete(`/${id}`, { headers: { Authorization: `Bearer ${store.state.token}` } });
    }
}