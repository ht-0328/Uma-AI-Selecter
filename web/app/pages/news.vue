<template>
  <div>
    <h1>ニュース一覧</h1>
    <button @click="syncNews">同期する</button>
    <div v-if="error">
      <p style="color: red;">取得失敗: {{ error }}</p>
    </div>
    <div v-else-if="status === 'pending'">
      <p>Loading...</p>
    </div>
    <div v-else>
      <div v-if="newsList && newsList.length > 0">
        <ul>
          <li v-for="news in newsList" :key="news.id">
            <h3>{{ news.title }}</h3>
            <p>{{ news.publishedAt }}</p>
            <p>{{ news.content }}</p>
          </li>
        </ul>
      </div>
      <div v-else>
        <p>ニュースはありません。</p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
interface NewsItem {
  id: number
  title: string
  content: string
  publishedAt: string
}

const config = useRuntimeConfig()
const { data: newsList, error, status, refresh } = await useFetch<NewsItem[]>(`${config.public.apiBaseUrl}/news`, {
  server: false
})

const syncNews = async () => {
  try {
    await $fetch(`${config.public.apiBaseUrl}/news/sync`, {
      method: 'POST'
    })
    await refresh()
  } catch (e) {
    console.error('Failed to sync news:', e)
    alert('同期に失敗しました')
  }
}
</script>
