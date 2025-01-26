<script>
import loginService from '../services/login-service';
export default {
    data() {
        return {
            newAccount: {
                username: '',
                email: '',
                password: ''
            },
            passwordConfirm: '',
            errorMessage: null
        }
    },
    methods: {
        register() {
            if (this.newAccount.password !== this.passwordConfirm) {
                this.errorMessage = 'Passwords do not match';
                return;
            }
            loginService.register(this.newAccount).then(registerResult => {
                if (!registerResult || registerResult.error) {
                    this.errorMessage = 'Error registering account';
                } else {
                    this.$router.push('/');
                }
            });
        }
    }
}
</script>

<template>
    <form @submit.prevent="register">
        <h1>Register</h1>
        <p class="error" v-if="errorMessage">{{ errorMessage }}</p>

        <fieldset>
            <label>
                <span>Username </span>
                <input type="text" v-model="newAccount.username" required>
            </label>
            <label>
                <span>Email </span>
                <input type="text" v-model="newAccount.email" required>
            </label>
            <label>
                <span>Password </span>
                <input type="password" v-model="newAccount.password" required>
            </label>
            <label>
                <span>Confirm Password </span>
                <input type="password" v-model="passwordConfirm" required>
            </label>
            <button type="submit">Register</button>
        </fieldset>
        <RouterLink to="/login">Back to Login</RouterLink>
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

input[type="checkbox"] {
    margin-right: 10px;
}

button {
    margin-top: 20px;
    width: 100%;
}

.error {
    color: red;
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