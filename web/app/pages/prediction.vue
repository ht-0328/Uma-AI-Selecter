<script setup lang="ts">
import { BarChart3, Activity } from 'lucide-vue-next'

const config = useRuntimeConfig()
const { data, pending, error } = await useFetch('/races/prediction', {
  baseURL: config.public.apiBaseUrl,
  lazy: true
})

const horses = computed(() => data.value?.horses || [])
const raceInfo = computed(() => data.value?.race || {
    name: "Loading...",
    course: "Loading...",
    trackCondition: "Loading...",
    distance: 0,
    track: "Loading..."
})

// Initialize scoring logic
const { weights, scoredHorses } = useRaceScoring(horses)
</script>

<template>
  <div class="min-h-screen bg-[#0b0e14] text-gray-100">
    <header class="border-b border-gray-800/60 bg-[#0d1117]/90 backdrop-blur-md sticky top-0 z-30">
      <div class="max-w-[1600px] mx-auto px-4 lg:px-6 py-3 flex items-center justify-between">
        <div class="flex items-center gap-3">
          <div class="flex items-center justify-center w-8 h-8 rounded-lg bg-emerald-500/10 border border-emerald-500/20">
            <BarChart3 :size="18" class="text-emerald-400" />
          </div>
          <div>
            <h1 class="text-sm lg:text-base font-bold text-gray-100 tracking-tight">
              Race Analyzer
            </h1>
            <p class="text-[11px] text-gray-500 hidden sm:block">
              重み付けベースのスコアリングツール (Nuxt 4 Port)
            </p>
          </div>
        </div>

        <div class="flex items-center gap-4">
          <div class="hidden md:flex items-center gap-2 text-[11px] text-gray-500">
            <Activity :size="12" class="text-emerald-500 animate-pulse" />
            <span>LIVE</span>
          </div>
          <div class="text-right">
            <p class="text-xs font-medium text-gray-300">{{ raceInfo.name }}</p>
            <p class="text-[11px] text-gray-500">
              {{ raceInfo.course }} / {{ raceInfo.trackCondition }}
            </p>
          </div>
        </div>
      </div>
    </header>

    <main class="max-w-[1600px] mx-auto px-4 lg:px-6 py-5 lg:py-6">
      <div v-if="pending" class="text-center py-8">
        <div class="animate-pulse flex space-x-4 justify-center">
          <div class="h-4 w-32 bg-gray-700 rounded"></div>
        </div>
        <p class="mt-2 text-gray-500">Loading race data...</p>
      </div>

      <div v-else-if="error" class="text-center py-8 text-red-500">
        <p>Error loading data: {{ error.message }}</p>
        <button @click="refreshNuxtData()" class="mt-4 px-4 py-2 bg-emerald-600 text-white rounded hover:bg-emerald-700">
          Retry
        </button>
      </div>

      <div v-else class="flex flex-col lg:flex-row gap-5 lg:gap-6">
        <div class="lg:w-[300px] xl:w-[320px] flex-shrink-0">
          <div class="lg:sticky lg:top-[72px]">
            <WeightSliders v-model:weights="weights" />
          </div>
        </div>

        <div class="flex-1 min-w-0">
          <RaceList :horses="scoredHorses" />
        </div>
      </div>
    </main>
  </div>
</template>
