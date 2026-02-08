export default defineEventHandler(() => {
  const jockeys = ['C.ルメール', '川田将雅', '武豊', '横山武史', '戸崎圭太', '松山弘平', '福永祐一', '岩田望来', 'M.デムーロ', '坂井瑠星']
  const horses = []

  for (let i = 1; i <= 14; i++) {
    const gate = Math.ceil(i / 2) // Simple gate assignment
    const horseNum = i

    // Generate random stats
    // last3f: 33.0 to 35.0
    const last3f = 33.0 + Math.random() * 2.0

    // Scores 1-10
    const position_score = Math.floor(Math.random() * 10) + 1
    const gate_score = Math.floor(Math.random() * 10) + 1
    const course_score = Math.floor(Math.random() * 10) + 1
    const jockey_score = Math.floor(Math.random() * 10) + 1

    // Passing order (e.g., "3-3-2-1")
    const p1 = Math.floor(Math.random() * 14) + 1
    const p2 = Math.floor(Math.random() * 14) + 1
    const p3 = Math.floor(Math.random() * 14) + 1
    const p4 = Math.floor(Math.random() * 14) + 1
    const passing_order = `${p1}-${p2}-${p3}-${p4}`

    horses.push({
      id: i,
      name: `Mock Horse ${i}`,
      gate_number: gate,
      horse_number: horseNum,
      jockey_name: jockeys[Math.floor(Math.random() * jockeys.length)],
      last3f: parseFloat(last3f.toFixed(1)),
      position_score,
      gate_score,
      course_score,
      jockey_score,
      passing_order
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
