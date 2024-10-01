<script>
import loginService from '../services/login-service';
export default {
    data() {
        return {
            username: '',
            password: '',
            rememberMe: false,
            errorMessage: null
        }
    },
    methods: {
        login() {
            loginService.login(this.username, this.password, this.rememberMe).then(loginResult => {
                if (!loginResult) {
                    this.errorMessage = 'Invalid username or password';
                } else {
                    this.$router.push('/');
                }
            });
        }
    }
}
</script>

<template>
    <form @submit.prevent="login">
        <h1>Login</h1>
        <p class="error" v-if="errorMessage">{{ errorMessage }}</p>

        <fieldset>
            <label>
                <span>Username </span>
                <input type="text" v-model="username" required>
            </label>
            <label>
                <span>Password </span>
                <input type="password" v-model="password" required>
            </label>
            <label>
                <input type="checkbox" v-model="rememberMe">
                <span>Remember me</span>
            </label>
            <button type="submit">Login</button>
        </fieldset>
        <RouterLink to="/register">Register a new account</RouterLink>
    </form>
</template>

<style scoped>
    form {
        width: 500px;
        margin: 50px auto 0 auto;
        padding: 50px;
        border: 1px solid #777;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 1rem;
        box-shadow: var(--shadow-3);
    }

    fieldset {
        border: none;
        display: grid;
        gap: 0.5rem;
        grid-template-columns: auto auto;
    }

    label {
        width: 80%;
        grid-column: span 2;
        display: grid;
        grid-template-columns: subgrid;
        gap: 1rem;
    }

    label:has(input[type="checkbox"]) {
        display: flex;
        width: 100%;
        justify-content: center;
    }

    button {
        grid-column: 2;
        width: 100%;
    }

@media (max-width: 1024) {
    .layout {
        flex-direction: column;
        align-items: flex-start;
    }
}

@media (max-width: 425px) {
    h1 {
        font-size: 24px;
        text-align: center;
    }

    form {
        max-width: 375px;
        padding: 15px; 
        box-shadow: var(--shadow-2); 
        margin: 5px;
        padding: 5px;
    }

    label {
        font-size: 14px;
    }

    button {
        padding: 12px;
        font-size: 14px;
    }

    button {
        padding: 5px;
    }
}
</style>