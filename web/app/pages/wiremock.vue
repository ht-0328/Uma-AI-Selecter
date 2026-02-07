<template>
  <div>
    <h1>WireMock Connectivity Check</h1>
    <div v-if="error">
      <p style="color: red;">取得失敗</p>
    </div>
    <div v-else-if="data">
      <pre>{{ data }}</pre>
    </div>
    <div v-else>
      <p>Loading...</p>
    </div>
  </div>
</template>

<script setup lang="ts">
const config = useRuntimeConfig()
const { data, error } = await useFetch(`${config.public.apiBaseUrl}/wiremock/ping`, {
  server: false
})
</script>
