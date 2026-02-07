<script setup lang="ts">
import type { ScoredHorse } from '~/composables/useRaceScoring'

defineProps<{
  horses: ScoredHorse[]
}>()

const stats = ['speed', 'stamina', 'power', 'guts', 'wisdom'] as const

const getConditionText = (cond: number) => {
  const conditions = ['Worst', 'Bad', 'Normal', 'Good', 'Best']
  return conditions[cond] || 'Unknown'
}

const getConditionColor = (cond: number) => {
    switch(cond) {
        case 4: return 'text-pink-500 font-bold' // Best (Zesshouchou)
        case 3: return 'text-orange-500' // Good
        case 2: return 'text-yellow-500' // Normal
        case 1: return 'text-blue-500' // Bad
        case 0: return 'text-purple-500' // Worst
        default: return 'text-gray-500'
    }
}
</script>

<template>
  <div class="bg-white p-4 rounded shadow overflow-x-auto">
    <h3 class="text-lg font-bold mb-4">Ranking</h3>
    <table class="w-full text-left text-sm text-gray-500">
      <thead class="text-xs text-gray-700 uppercase bg-gray-50">
        <tr>
          <th class="px-4 py-2">Rank</th>
          <th class="px-4 py-2">Name</th>
          <th class="px-4 py-2 text-right">Score</th>
          <th v-for="stat in stats" :key="stat" class="px-4 py-2 text-right capitalize">{{ stat }}</th>
          <th class="px-4 py-2">Strategy</th>
          <th class="px-4 py-2">Cond</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(horse, index) in horses" :key="horse.id" class="bg-white border-b hover:bg-gray-50">
          <td class="px-4 py-2 font-bold">{{ index + 1 }}</td>
          <td class="px-4 py-2 font-medium text-gray-900">{{ horse.name }}</td>
          <td class="px-4 py-2 text-right font-bold text-blue-600">{{ horse.score.toFixed(1) }}</td>
          <td v-for="stat in stats" :key="stat" class="px-4 py-2 text-right">{{ horse[stat] }}</td>
          <td class="px-4 py-2">{{ horse.strategy }}</td>
          <td class="px-4 py-2">
            <span :class="getConditionColor(horse.condition)">
                {{ getConditionText(horse.condition) }}
            </span>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
