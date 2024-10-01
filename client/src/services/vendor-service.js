import axios from "axios"
import { store } from '../main';

const http = axios.create({
    baseURL: "http://localhost:8080/api/vendor"
});

export default {

    listVendors() {
        return http.get("", { headers: { Authorization: `Bearer ${store.state.token}` } });
    },

    getVendor(id) {
        return http.get("/" + id, { headers: { Authorization: `Bearer ${store.state.token}` } });
    },

    addVendor(vendor) {
        return http.post("", vendor, { headers: { Authorization: `Bearer ${store.state.token}` } });
    },

    updateVendor(id, vendor) {
        return http.put(`/${id}`, vendor, { headers: { Authorization: `Bearer ${store.state.token}` } });
    },
    deleteVendor(id) {
        return http.delete(`/${id}`, { headers: { Authorization: `Bearer ${store.state.token}` } });
    }
}