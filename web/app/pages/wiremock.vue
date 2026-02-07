<template>
  <div>
    <h1>WireMock Status</h1>
    <div v-if="status === 'pending'">
      <p>Loading...</p>
    </div>
    <div v-else-if="error">
      <p style="color: red;">取得失敗</p>
    </div>
    <div v-else>
      <pre>{{ data }}</pre>
    </div>
  </div>
</template>

<script setup lang="ts">
const config = useRuntimeConfig()
const { data, error, status } = await useFetch(`${config.public.apiBaseUrl}/wiremock/ping`, { lazy: true })
</script>
