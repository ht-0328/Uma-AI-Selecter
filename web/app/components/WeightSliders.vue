<script setup lang="ts">
import { SlidersHorizontal, RotateCcw } from 'lucide-vue-next'

const weights = defineModel<{
  speed: number
  stamina: number
  power: number
  guts: number
  wisdom: number
}>('weights', { required: true })

const stats = ['speed', 'stamina', 'power', 'guts', 'wisdom'] as const

const statLabels: Record<string, string> = {
  speed: 'スピード',
  stamina: 'スタミナ',
  power: 'パワー',
  guts: '根性',
  wisdom: '賢さ'
}

const statDescriptions: Record<string, string> = {
  speed: '最高速度と加速力',
  stamina: '持久力と粘り強さ',
  power: 'コース取りとパワー',
  guts: '競り合いの強さ',
  wisdom: 'スキル発動率と賢さ'
}

const resetWeights = () => {
    stats.forEach(s => weights.value[s] = 50)
}
</script>

<template>
  <aside class="bg-[#111827]/80 backdrop-blur-sm border border-gray-800/60 rounded-xl p-4 lg:p-5 space-y-5">
    <div class="flex items-center justify-between">
      <div class="flex items-center gap-2">
        <SlidersHorizontal :size="18" class="text-emerald-400" />
        <h2 class="text-base font-bold text-gray-100 tracking-tight">重み設定</h2>
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
            <span class="text-xs font-mono text-emerald-400 tabular-nums">{{ weights[stat] }}%</span>
        </div>
        <input
            v-model.number="weights[stat]"
            type="range"
            min="0"
            max="100"
            class="w-full h-1.5 bg-gray-700 rounded-lg appearance-none cursor-pointer [&::-webkit-slider-thumb]:appearance-none [&::-webkit-slider-thumb]:w-3 [&::-webkit-slider-thumb]:h-3 [&::-webkit-slider-thumb]:rounded-full [&::-webkit-slider-thumb]:bg-emerald-500 hover:[&::-webkit-slider-thumb]:bg-emerald-400 [&::-webkit-slider-thumb]:transition-colors"
        />
        <p class="text-[10px] text-gray-500">{{ statDescriptions[stat] }}</p>
      </div>
    </div>
  </aside>
</template>
