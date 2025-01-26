<script>
  export default {
  }
</script>

<template>
  <section class="layout">
      
    <article class="info">

      <h2>About Our Store</h2>

        <p>As your local Ace Hardware,
            we are one of 5,000+ Ace stores locally owned and operated across the globe.
            But we are not just about numbers. We are about helping neighbors,
            because each one of our stores is a part of your community.
            We are all committed to being "the Helpful Place" by offering our customers personal service,
            quality products and a convenient shopping experience from the local experts who know you best.
            As the helpful hardware folks in your neighborhood,
            we promise that helping you is the most important thing we have to do today.
        </p>

      <h2>Store Services</h2>
    
        <ul>
          <li>Paint Color Matching</li>
          <li>Propane Refill or Exchange</li>
          <li>Auto Key Programming & Cutting</li>
          <li>Key Cutting</li>
          <li>Assembly</li>
          <li>Glass Cutting</li>
          <li>Pipe Cutting & Threading</li>
          <li>Rental - Carpet Cleaning</li>
          <li>In-Store Lock Servicing</li>
          <li>Acrylic Sheet Cutting</li>
          <li>Screen Repair</li>
          <li>Gift Card</li>
        </ul>
      </article>
    
      <article class="images">
        <img src="../images/ace.jpg" alt="Ace Front Store">
        <img src="../images/paint.jpg" alt="Ace Paint display">
        <img src="../images/flowers.jpg" alt="Image of Flowers">
      </article>>
    
    </section>
      
</template>

<style scoped>

@import "open-props/style";

  .layout {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 10px;
  }

  .info, .images {
    padding: 20px
  }

  h2 {
  color: var(--red-8);
  border-bottom: 2px solid var(--red-8);
  padding: 10px;
  margin: 10px;
}

  img {
    display: flex;
    max-width: 400px;
    height: auto;
    border-radius: 8px;
    padding: 10px;
    margin: 10px;
}

  ul li {
    background: var(--gray-2);
    margin: 5px;
    padding: 10px;
    border-radius: 5px;
}

@media (max-width: 1024px) {
  .layout {
    flex-direction: column;
    align-items: center;
  }

  .info, .images {
    width: 100%;
    padding: 10px;
  }

  .images img {
    max-width: 100%;
    margin-bottom: 10px;
  }

  ul li {
    font-size: 14px;
    padding: 10px;
  }
}

@media (max-width: 425px) {
  .layout {
    padding: 5px;
  }

  .info, .images {
    width: 100%;
    padding: 5px
  }

  .images img {
    max-width: 100%;
    height: auto;
    margin-bottom: 5px;
  }

  ul li {
    font-size: 12px;
    padding: 8px;
  }
}
</style>