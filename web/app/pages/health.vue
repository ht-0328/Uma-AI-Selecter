<script setup lang="ts">
const config = useRuntimeConfig()
const { data, status, error } = await useFetch(`${config.public.apiBaseUrl}/health`)
</script>

<template>
  <div>
    <h1>Health Check</h1>
    <div v-if="status === 'pending'">Loading...</div>
    <div v-else-if="status === 'error'">
      Error: {{ error }}
    </div>
    <div v-else>
      Result: {{ data }}
    </div>
  </div>
</template>
