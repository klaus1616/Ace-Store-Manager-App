<script>
import { RouterView, RouterLink } from 'vue-router';
import loginService from './services/login-service';
import TopHeader from './components/topHeader.vue';
export default {
  components: {
    RouterView,
    RouterLink,
    TopHeader
  },
  methods: {
    logout() {
      return loginService.logout();
    },
  }
}
</script>

<template>
  <header>
    <TopHeader />
    <nav>
      <menu>
        <li>
          <router-link to="/">Home</router-link>
        </li>
        <li>
          <router-link to="/vendor">Outside Services</router-link>
        </li>
        <li>
          <router-link to="/employeePortal"> Employee Portal</router-link>
        </li>
      </menu>
      <menu>
        <li v-if="$store.state.user">
          Welcome, {{ $store.state.user?.username }}
        </li>
        <li v-if="$store.state.token">
          <a href="#" @click="logout">Logout</a>
        </li>
        <li v-else>
          <router-link to="/login">Login</router-link>
        </li>
      </menu>
    </nav>
  </header>
  <main>
    <RouterView />
  </main>
  <footer>
    <p>&copy; 2024</p>
  </footer>
</template>

<style scoped>
/* Styles for content in this component are in main.css, as they are top-level (directly below #app) */
</style>