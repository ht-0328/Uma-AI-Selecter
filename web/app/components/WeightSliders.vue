<script setup lang="ts">
const weights = defineModel<{
  speed: number
  stamina: number
  power: number
  guts: number
  wisdom: number
}>('weights', { required: true })

const stats = ['speed', 'stamina', 'power', 'guts', 'wisdom'] as const
</script>

<template>
  <div class="bg-white p-4 rounded shadow">
    <h3 class="text-lg font-bold mb-4">Weight Settings</h3>
    <div v-for="stat in stats" :key="stat" class="mb-4">
      <div class="flex justify-between mb-1">
        <label class="block text-sm font-medium text-gray-700 capitalize">{{ stat }}</label>
        <span class="text-sm text-gray-500">{{ weights[stat] }}</span>
      </div>
      <input
        v-model.number="weights[stat]"
        type="range"
        min="0"
        max="100"
        class="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer accent-blue-600"
      />
    </div>
  </div>
</template>
