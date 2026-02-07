export interface Horse {
  id: number
  name: string
  speed: number
  stamina: number
  power: number
  guts: number
  wisdom: number
  strategy: string
  condition: number
}

export interface ScoredHorse extends Horse {
  score: number
}

export const useRaceScoring = (horses: Ref<Horse[]>) => {
  const weights = reactive({
    speed: 50,
    stamina: 50,
    power: 50,
    guts: 50,
    wisdom: 50
  })

  const scoredHorses = computed(() => {
    return horses.value.map(horse => {
      const totalWeight = weights.speed + weights.stamina + weights.power + weights.guts + weights.wisdom
      const score = totalWeight > 0 ? (
        horse.speed * weights.speed +
        horse.stamina * weights.stamina +
        horse.power * weights.power +
        horse.guts * weights.guts +
        horse.wisdom * weights.wisdom
      ) / totalWeight : 0

      return { ...horse, score }
    }).sort((a, b) => b.score - a.score)
  })

  return {
    weights,
    scoredHorses
  }
}
