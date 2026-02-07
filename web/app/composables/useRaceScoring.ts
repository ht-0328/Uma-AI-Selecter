export const useRaceScoring = () => {
  const calculateScore = (horse: any, weights: any) => {
    let score = 0;
    // Simple weighted sum
    score += (horse.stats.speed || 0) * (weights.speed || 0);
    score += (horse.stats.stamina || 0) * (weights.stamina || 0);
    score += (horse.stats.power || 0) * (weights.power || 0);
    score += (horse.stats.guts || 0) * (weights.guts || 0);
    score += (horse.stats.wisdom || 0) * (weights.wisdom || 0);
    return Math.round(score); // Round to integer for display
  };

  const sortHorses = (horses: any[], weights: any) => {
    if (!horses) return [];
    return horses.map(horse => ({
      ...horse,
      score: calculateScore(horse, weights)
    })).sort((a, b) => b.score - a.score); // Descending order
  };

  return {
    calculateScore,
    sortHorses
  };
};
