import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import { createAppRouter } from './router'
import { createAppStore } from './store'
import { BootstrapIconsPlugin } from "bootstrap-icons-vue";
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import loginService from './services/login-service'

export const router = createAppRouter();
export const store = createAppStore();
const vuetify = createVuetify({ components, directives });


loginService.restoreLogin().then(() => {
    const app = createApp(App);
    app.use(router);
    app.use(store);
    app.use(BootstrapIconsPlugin);
    app.use(vuetify);

    app.mount('#app')
});

