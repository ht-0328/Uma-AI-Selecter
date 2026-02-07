<template>
  <div class="min-h-screen bg-gray-100 p-8">
    <h1 class="text-3xl font-bold mb-6 text-gray-800">Mock Race Prediction</h1>

    <div v-if="status === 'pending'" class="text-center text-gray-600">Loading...</div>
    <div v-else-if="error" class="text-center text-red-600">Error loading data: {{ error }}</div>
    <div v-else class="grid grid-cols-1 md:grid-cols-3 gap-8">

      <!-- Sliders Panel -->
      <div class="bg-white p-6 rounded-lg shadow-md md:col-span-1">
        <h2 class="text-xl font-semibold mb-4 text-gray-700">Weight Settings</h2>
        <div v-for="(weight, stat) in weights" :key="stat" class="mb-4">
          <label class="block text-sm font-medium text-gray-700 capitalize mb-1">
            {{ stat }} ({{ weight }})
          </label>
          <input
            type="range"
            v-model.number="weights[stat]"
            min="0"
            max="10"
            class="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer"
          />
        </div>
      </div>

      <!-- Results Panel -->
      <div class="bg-white p-6 rounded-lg shadow-md md:col-span-2">
        <h2 class="text-xl font-semibold mb-4 text-gray-700">Prediction Results</h2>
        <div class="overflow-x-auto">
          <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
              <tr>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Rank</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Horse</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Score</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Odds</th>
              </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">
              <tr v-for="(horse, index) in sortedHorses" :key="horse.id">
                <td class="px-6 py-4 whitespace-nowrap text-sm font-bold text-gray-900">#{{ index + 1 }}</td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{{ horse.name }}</td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-blue-600 font-semibold">{{ horse.score }}</td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{{ horse.odds }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

    </div>
  </div>
</template>

<script setup lang="ts">
const { data, status, error } = await useFetch('/api/mock/races')

const weights = ref({
  speed: 5,
  stamina: 5,
  power: 5,
  guts: 5,
  wisdom: 5
})

const { calculateScore } = useRaceScoring()

const sortedHorses = computed(() => {
  if (!data.value || !data.value.horses) return []

  // Clone array to sort
  const horses = [...data.value.horses]

  return horses.map(horse => ({
    ...horse,
    score: calculateScore(horse, weights.value)
  })).sort((a, b) => b.score - a.score)
})
</script>
