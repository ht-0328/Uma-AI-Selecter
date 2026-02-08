<script setup lang="ts">
import { TrendingUp, Trophy, ArrowUpDown } from 'lucide-vue-next'
import type { ScoredHorse } from '~/composables/useRaceScoring'

defineProps<{
  horses: ScoredHorse[]
}>()

const getRankClass = (rank: number) => {
  if (rank === 1) return 'text-amber-400 font-black'
  if (rank === 2) return 'text-gray-300 font-bold'
  if (rank === 3) return 'text-orange-400 font-bold'
  return 'text-gray-500 font-medium'
}

const getRankBg = (rank: number) => {
  if (rank === 1) return 'bg-amber-400/5 border-l-2 border-l-amber-400/40'
  if (rank === 2) return 'bg-gray-400/5 border-l-2 border-l-gray-400/30'
  if (rank === 3) return 'bg-orange-400/5 border-l-2 border-l-orange-400/30'
  return 'border-l-2 border-l-transparent'
}

const getGateColor = (gate: number) => {
  switch (gate) {
    case 1: return 'bg-white text-gray-900'
    case 2: return 'bg-gray-900 text-white border border-gray-700'
    case 3: return 'bg-red-600 text-white'
    case 4: return 'bg-blue-600 text-white'
    case 5: return 'bg-yellow-500 text-gray-900'
    case 6: return 'bg-emerald-600 text-white'
    case 7: return 'bg-orange-500 text-white'
    case 8: return 'bg-pink-500 text-white'
    default: return 'bg-gray-700 text-gray-300'
  }
}

const getLast3FColor = (time: number) => {
    return time < 34.0 ? 'text-emerald-400 font-bold' : 'text-gray-300'
}

// Calculate max score for bar chart
const maxScore = computed(() => {
    // Since scores are roughly 0-10 or 0-50 depending on weights.
    // Weights are 0-10. Max score = 10 (if normalized).
    // Let's assume max possible score is 10 * 5 / 5 = 10?
    // Wait, in composable: sum(score * weight) / sum(weight).
    // If all scores are 10, result is 10.
    return 10;
})

</script>

<template>
  <div class="bg-[#111827]/80 backdrop-blur-sm border border-gray-800/60 rounded-xl overflow-hidden">
    <div class="flex items-center justify-between px-4 lg:px-5 py-3.5 border-b border-gray-800/60">
      <div class="flex items-center gap-2">
        <TrendingUp :size="18" class="text-emerald-400" />
        <h2 class="text-base font-bold text-gray-100 tracking-tight">Analysis Grid</h2>
      </div>
      <div class="flex items-center gap-1.5 text-[11px] text-gray-500">
        <ArrowUpDown :size="12" />
        <span>スコア順</span>
      </div>
    </div>

    <div class="overflow-x-auto">
      <table class="w-full text-sm">
        <thead>
          <tr class="border-b border-gray-800/60 text-[11px] uppercase tracking-wider text-gray-500">
            <th class="px-3 lg:px-4 py-2.5 text-center w-12">順位</th>
            <th class="px-3 lg:px-4 py-2.5 text-center w-20">枠/馬番</th>
            <th class="px-3 lg:px-4 py-2.5 text-left">馬名</th>
            <th class="px-3 lg:px-4 py-2.5 text-left w-32">総合スコア</th>
            <th class="px-3 lg:px-4 py-2.5 text-right">上がり3F</th>
            <th class="px-3 lg:px-4 py-2.5 text-center">脚質 (通過順)</th>
            <th class="px-3 lg:px-4 py-2.5 text-left">騎手</th>
          </tr>
        </thead>
        <tbody class="text-gray-300">
          <tr
            v-for="(horse, index) in horses"
            :key="horse.id"
            class="border-b border-gray-800/30 hover:bg-gray-800/30 transition-colors duration-150"
            :class="getRankBg(index + 1)"
          >
            <td class="px-3 lg:px-4 py-2.5 text-center">
              <div class="flex items-center justify-center">
                <Trophy v-if="index + 1 <= 3" :size="14" class="mr-0.5" :class="getRankClass(index + 1)" />
                <span class="text-sm tabular-nums" :class="getRankClass(index + 1)">
                  {{ index + 1 }}
                </span>
              </div>
            </td>

            <td class="px-3 lg:px-4 py-2.5">
              <div class="flex items-center justify-center gap-1">
                <span :class="getGateColor(horse.gate_number)" class="w-5 h-5 flex items-center justify-center text-[10px] font-bold rounded shadow-sm">
                  {{ horse.gate_number }}
                </span>
                <span :class="getGateColor(horse.gate_number)" class="w-5 h-5 flex items-center justify-center text-[10px] font-bold rounded shadow-sm">
                  {{ horse.horse_number }}
                </span>
              </div>
            </td>

            <td class="px-3 lg:px-4 py-2.5">
              <span class="font-bold text-gray-100 whitespace-nowrap">{{ horse.name }}</span>
            </td>

            <td class="px-3 lg:px-4 py-2.5">
               <div class="flex items-center gap-2">
                 <div class="flex-1 h-1.5 bg-gray-700 rounded-full overflow-hidden w-16">
                   <div class="h-full bg-emerald-500 rounded-full" :style="{ width: `${(horse.score / maxScore) * 100}%` }"></div>
                 </div>
                 <span class="font-bold text-emerald-400 tabular-nums text-sm">{{ horse.score.toFixed(1) }}</span>
               </div>
            </td>

            <td class="px-3 lg:px-4 py-2.5 text-right tabular-nums">
                <span :class="getLast3FColor(horse.last3f)">{{ horse.last3f.toFixed(1) }}</span>
            </td>

            <td class="px-3 lg:px-4 py-2.5 text-center tabular-nums text-xs text-gray-400">
                {{ horse.passing_order }}
            </td>

            <td class="px-3 lg:px-4 py-2.5 text-sm text-gray-300">
              {{ horse.jockey_name }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
