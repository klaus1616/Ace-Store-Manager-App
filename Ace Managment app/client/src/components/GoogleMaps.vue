<script>
    export default {
        created() {
            this.$getLocation().then((coordinates) => {
                console.log(coordinates);
            })
            .catch((error) => {
                console.log(error);
            });
        }
    }
</script>

<template>
    <div>
        <p>Google maps component</p>
    </div>
</template>