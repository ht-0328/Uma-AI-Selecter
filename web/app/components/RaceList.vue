<script setup lang="ts">
import { TrendingUp, Trophy, ArrowUpDown } from 'lucide-vue-next'
import type { ScoredHorse } from '~/composables/useRaceScoring'

defineProps<{
  horses: ScoredHorse[]
}>()

const stats = ['speed', 'stamina', 'power', 'guts', 'wisdom'] as const

const statLabels: Record<string, string> = {
  speed: 'スピード',
  stamina: 'スタミナ',
  power: 'パワー',
  guts: '根性',
  wisdom: '賢さ'
}

const getConditionText = (cond: number) => {
  const conditions = ['絶不調', '不調', '普通', '好調', '絶好調']
  return conditions[cond] || '不明'
}

const getStrategyText = (strategy: string) => {
    const strategies: Record<string, string> = {
        'Runner': '逃げ',
        'Leader': '先行',
        'Betweener': '差し',
        'Chaser': '追込'
    }
    return strategies[strategy] || strategy
}

const getConditionColor = (cond: number) => {
    switch(cond) {
        case 4: return 'text-pink-400 font-bold'
        case 3: return 'text-orange-400'
        case 2: return 'text-yellow-400'
        case 1: return 'text-blue-400'
        case 0: return 'text-purple-400'
        default: return 'text-gray-400'
    }
}

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

const maxScore = computed(() => {
    // Just an approximation for visualization if we had a bar.
    return 1200;
})
</script>

<template>
  <div class="bg-[#111827]/80 backdrop-blur-sm border border-gray-800/60 rounded-xl overflow-hidden">
    <div class="flex items-center justify-between px-4 lg:px-5 py-3.5 border-b border-gray-800/60">
      <div class="flex items-center gap-2">
        <TrendingUp :size="18" class="text-emerald-400" />
        <h2 class="text-base font-bold text-gray-100 tracking-tight">分析グリッド</h2>
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
            <th class="px-3 lg:px-4 py-2.5 text-left">馬名</th>
            <th class="px-3 lg:px-4 py-2.5 text-right min-w-[100px]">スコア</th>
            <th v-for="stat in stats" :key="stat" class="px-3 lg:px-4 py-2.5 text-right">{{ statLabels[stat] }}</th>
            <th class="px-3 lg:px-4 py-2.5 text-center">脚質</th>
            <th class="px-3 lg:px-4 py-2.5 text-center">調子</th>
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
              <span class="font-bold text-gray-100 whitespace-nowrap">{{ horse.name }}</span>
            </td>

            <td class="px-3 lg:px-4 py-2.5 text-right">
               <span class="font-bold text-emerald-400 tabular-nums">{{ horse.score.toFixed(1) }}</span>
            </td>

            <td v-for="stat in stats" :key="stat" class="px-3 lg:px-4 py-2.5 text-right tabular-nums text-gray-400">
                {{ horse[stat] }}
            </td>

            <td class="px-3 lg:px-4 py-2.5 text-center">
                <span class="text-xs px-2 py-0.5 rounded-full bg-gray-800 border border-gray-700">
                    {{ getStrategyText(horse.strategy) }}
                </span>
            </td>

            <td class="px-3 lg:px-4 py-2.5 text-center">
              <span :class="getConditionColor(horse.condition)" class="text-xs">
                {{ getConditionText(horse.condition) }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
