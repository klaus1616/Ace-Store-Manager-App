import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';
import EmployeePortal from '../views/EmployeePortal.vue';
import EmployeeDetails from '../views/EmployeeDetails.vue';
import Vendor from '../views/Vendor.vue';
import { store } from '../main';

export function createAppRouter() {
    const router = createRouter({
        history: createWebHistory(),
        routes: [
            {
                path: '/',
                name: 'Home',
                component: Home,
                meta: {
                    requireAuth: false,
                }
            },
            {
                path: '/employeePortal',
                name: 'EmployeePortal',
                component: EmployeePortal,
                meta: {
                    requireAuth: true,
                }
            },
            {
                path: '/employee/:id',
                name: 'EmployeeDetails',
                component: EmployeeDetails,
                meta: {
                    requireAuth: true,
                }
            },
            {
            path: '/vendor',
            name: 'Vendor',
            component: Vendor,
            meta: {
                requireAuth: false,
            }
            },
            {
                path: '/login',
                name: 'Login',
                component: Login,
                meta: {
                    requireAuth: false,
                }
            },
            {
                path: '/register',
                name: 'Register',
                component: Register,
                meta: {
                    requireAuth: false,
                }
            }
        ]
    });

    router.beforeEach((to, from, next) => {
        if (to.meta.requireAuth === false) {
            next();
        } else if (to.meta.requireAuth  && store.state.token === null) {
            next('/login');
        } else {
            next();
        }
    });
    
    return router;
}