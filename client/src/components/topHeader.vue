<script>
export default {
  name: 'TopHeader'
};
</script>

<template>
  <section>
    <img class="logo" src="../images/ace-logo.jpg" alt="Ace hardware Logo" />
    <h1 class="title">Neradt Ace Hardware</h1>
    <div class="spacer"></div>
  </section>
</template>

<style scoped>

section {
  display: flex;
  align-items: center;
  height: 100px;
  background-image: var(--gradient-16);
  padding: 10px;
  
}

.title {
  flex-grow: 1;
  text-align: center;
  font-size: 44px;
  color: var(--gray-4);
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  margin: 0;
}

.logo {
  height: 80px;
  width: 150px;
  border: 3px solid var(--gray-4);
  border-radius: 50%;
}
.spacer {
  width: 150px;
}

@media (max-width: 1024px) {
  section {
    flex-direction: column;
    height: auto;
    padding: 10px;
    text-align: center;
  }

  .logo {
    height: 60px;
    width: 120px;
    margin: 5px;
  }

  .title {
    font-size: 36px;
  }

  .spacer {
    width: 0;
    height: 20px; 
  }
}

@media (max-width: 425px) {
  section {
    flex-direction: column;
    height: auto;
    padding: 5px;
    text-align: center;
  }

  .logo {
    height: 50px;
    width: 100px;
    margin-bottom: 5px;
  }

  .title {
    font-size: 28px;
  }

  .spacer {
    width: 0;
    height: 10px;
  }
}
</style>