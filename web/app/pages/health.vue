<template>
  <div>
    <h1>Health Check</h1>
    <div v-if="error">
      <p style="color: red;">Failed to fetch health status</p>
      <pre>{{ error }}</pre>
    </div>
    <div v-else-if="data">
      <p>API Status: {{ data }}</p>
    </div>
    <div v-else>
      <p>Loading...</p>
    </div>
  </div>
</template>

<script setup lang="ts">
const config = useRuntimeConfig()
const { data, error } = await useFetch(`${config.public.apiBaseUrl}/health`)
</script>
