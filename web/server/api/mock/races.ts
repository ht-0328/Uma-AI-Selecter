export default defineEventHandler(() => {
  const strategies = ['Runner', 'Leader', 'Betweener', 'Chaser']
  const horses = []

  // Seed random for consistent results? No, random is fine for mock.
  // Actually, let's make it somewhat deterministic or just random.

  for (let i = 1; i <= 16; i++) {
    horses.push({
      id: i,
      name: `Mock Horse ${i}`,
      speed: Math.floor(Math.random() * 600) + 600, // 600-1200
      stamina: Math.floor(Math.random() * 600) + 600,
      power: Math.floor(Math.random() * 600) + 600,
      guts: Math.floor(Math.random() * 600) + 600,
      wisdom: Math.floor(Math.random() * 600) + 600,
      strategy: strategies[Math.floor(Math.random() * strategies.length)],
      condition: Math.floor(Math.random() * 5) // 0: Worst, 4: Best
    })
  }

  return {
    race: {
        name: "Mock G1 Race (2400m Turf)",
        distance: 2400,
        track: "Turf"
    },
    horses: horses
  }
})
