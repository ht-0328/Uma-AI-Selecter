<script setup lang="ts">
import { SlidersHorizontal, RotateCcw } from 'lucide-vue-next'

const weights = defineModel<{
  last3f: number
  position: number
  gate: number
  course: number
  jockey: number
}>('weights', { required: true })

const stats = ['last3f', 'position', 'gate', 'course', 'jockey'] as const

const statLabels: Record<string, string> = {
  last3f: '上がり3F力',
  position: '先行力',
  gate: '枠順有利度',
  course: 'コース実績',
  jockey: '騎手信頼度'
}

const statDescriptions: Record<string, string> = {
  last3f: '末脚の切れ味',
  position: '4コーナー通過順位の良さ',
  gate: '内枠・外枠の有利不利',
  course: '当該コースでの過去成績',
  jockey: '騎手の勝率'
}

const resetWeights = () => {
    stats.forEach(s => weights.value[s] = 5)
}
</script>

<template>
  <aside class="bg-[#111827]/80 backdrop-blur-sm border border-gray-800/60 rounded-xl p-4 lg:p-5 space-y-5">
    <div class="flex items-center justify-between">
      <div class="flex items-center gap-2">
        <SlidersHorizontal :size="18" class="text-emerald-400" />
        <h2 class="text-base font-bold text-gray-100 tracking-tight">Logic Panel</h2>
      </div>
      <button
        class="p-1.5 rounded-lg text-gray-400 hover:text-gray-200 hover:bg-gray-800/60 transition-colors"
        title="リセット"
        @click="resetWeights"
      >
        <RotateCcw :size="15" />
      </button>
    </div>

    <div class="space-y-4">
      <div v-for="stat in stats" :key="stat" class="space-y-3 p-3 rounded-lg bg-gray-800/20 border border-gray-800/40 hover:border-gray-700/60 transition-colors">
        <div class="flex justify-between items-center">
            <label class="text-xs font-medium text-gray-300">{{ statLabels[stat] }}</label>
            <span class="text-xs font-mono text-emerald-400 tabular-nums">{{ weights[stat] }}</span>
        </div>
        <input
            v-model.number="weights[stat]"
            type="range"
            min="0"
            max="10"
            step="1"
            class="w-full h-1.5 bg-gray-700 rounded-lg appearance-none cursor-pointer [&::-webkit-slider-thumb]:appearance-none [&::-webkit-slider-thumb]:w-3 [&::-webkit-slider-thumb]:h-3 [&::-webkit-slider-thumb]:rounded-full [&::-webkit-slider-thumb]:bg-emerald-500 hover:[&::-webkit-slider-thumb]:bg-emerald-400 [&::-webkit-slider-thumb]:transition-colors"
        />
        <p class="text-[10px] text-gray-500">{{ statDescriptions[stat] }}</p>
      </div>
    </div>
  </aside>
</template>
