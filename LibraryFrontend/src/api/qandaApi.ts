// QandA API
import { http } from './http'

export interface Qanda {
  id: number
  title: string
  content: string
  answer: string | null
  status: '答え完了' | '答え待機'
  createdAt: string
}

export type QandaInput = Pick<Qanda, 'title' | 'content'>

export const qandaApi = {
  /** Q&A一覧を取得する */
  list(): Promise<Qanda[]> {
    return http.get<Qanda[]>('/qanda')
  },

  /** Q&A詳細を取得する */
  get(id: number | string): Promise<Qanda> {
    return http.get<Qanda>(`/qanda/${id}`)
  },

  /** 質問を新規作成する */
  create(input: QandaInput): Promise<Qanda> {
    return http.post<Qanda>('/qanda', input)
  },

  /** 質問内容を更新する */
  update(id: number | string, input: QandaInput): Promise<Qanda> {
    return http.put<Qanda>(`/qanda/${id}`, input)
  },

  /** 回答を登録する */
  answer(id: number | string, answer: string): Promise<Qanda> {
    return http.patch<Qanda>(`/qanda/${id}/answer`, { answer })
  },

  /** 削除する */
  remove(id: number | string): Promise<void> {
    return http.delete<void>(`/qanda/${id}`)
  },
}
