<script setup lang="ts">
const { data: horses, pending, error } = await useFetch('/api/mock/races', {
  lazy: true,
  transform: (data: any) => data.horses
})

// Ensure we have a valid array ref even if loading or error
const safeHorses = computed(() => horses.value || [])

// Initialize scoring logic
const { weights, scoredHorses } = useRaceScoring(safeHorses)
</script>

<template>
  <div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-6">Race Prediction Prototype</h1>

    <div v-if="pending" class="text-center py-8">
      <div class="animate-pulse flex space-x-4 justify-center">
        <div class="h-4 w-32 bg-gray-200 rounded"></div>
      </div>
      <p class="mt-2 text-gray-500">Loading race data...</p>
    </div>

    <div v-else-if="error" class="text-center py-8 text-red-600">
      <p>Error loading data: {{ error.message }}</p>
      <button @click="refreshNuxtData()" class="mt-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">
        Retry
      </button>
    </div>

    <div v-else class="grid grid-cols-1 lg:grid-cols-4 gap-6">
      <div class="lg:col-span-1">
        <WeightSliders v-model:weights="weights" />
      </div>

      <div class="lg:col-span-3">
        <RaceList :horses="scoredHorses" />
      </div>
    </div>
  </div>
</template>
