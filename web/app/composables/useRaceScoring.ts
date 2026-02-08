export interface Horse {
  id: number
  name: string
  gateNumber: number
  horseNumber: number
  jockeyName: string
  last3f: number
  positionScore: number
  gateScore: number
  courseScore: number
  jockeyScore: number
  passingOrder: string
}

export interface ScoredHorse extends Horse {
  score: number
}

export const useRaceScoring = (horses: Ref<Horse[]>) => {
  const weights = reactive({
    last3f: 5,
    position: 5,
    gate: 5,
    course: 5,
    jockey: 5
  })

  const scoredHorses = computed(() => {
    return horses.value.map(horse => {
      // 上がり3Fを正規化: 33.0を最高点(10点)、36.0を最低点(0点)とする
      const last3fScore = Math.max(0, Math.min(10, (36.0 - horse.last3f) / (36.0 - 33.0) * 10))

      const totalWeight = weights.last3f + weights.position + weights.gate + weights.course + weights.jockey

      const score = totalWeight > 0 ? (
        last3fScore * weights.last3f +
        horse.positionScore * weights.position +
        horse.gateScore * weights.gate +
        horse.courseScore * weights.course +
        horse.jockeyScore * weights.jockey
      ) / totalWeight : 0

      return { ...horse, score }
    }).sort((a, b) => b.score - a.score)
  })

  return {
    weights,
    scoredHorses
  }
}
