export interface Horse {
  id: number
  name: string
  gate_number: number
  horse_number: number
  jockey_name: string
  last3f: number
  position_score: number
  gate_score: number
  course_score: number
  jockey_score: number
  passing_order: string
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
      // Normalize last3f: assume 33.0 is best (10 pts), 36.0 is worst (0 pts)
      const last3fScore = Math.max(0, Math.min(10, (36.0 - horse.last3f) / (36.0 - 33.0) * 10))

      const totalWeight = weights.last3f + weights.position + weights.gate + weights.course + weights.jockey

      const score = totalWeight > 0 ? (
        last3fScore * weights.last3f +
        horse.position_score * weights.position +
        horse.gate_score * weights.gate +
        horse.course_score * weights.course +
        horse.jockey_score * weights.jockey
      ) / totalWeight : 0

      return { ...horse, score }
    }).sort((a, b) => b.score - a.score)
  })

  return {
    weights,
    scoredHorses
  }
}
