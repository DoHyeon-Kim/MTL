// お知らせ(Announcement) API
import { http } from './http'

export interface Announcement {
  id: number
  title: string
  content: string
  createdAt: string
}

export type AnnouncementInput = Pick<Announcement, 'title' | 'content'>

export const announcementApi = {
  /** お知らせ一覧を取得する */
  list(): Promise<Announcement[]> {
    return http.get<Announcement[]>('/announcements')
  },

  /** お知らせ詳細を取得する */
  get(id: number | string): Promise<Announcement> {
    return http.get<Announcement>(`/announcements/${id}`)
  },

  /** お知らせを新規作成する */
  create(input: AnnouncementInput): Promise<Announcement> {
    return http.post<Announcement>('/announcements', input)
  },

  /** お知らせを更新する */
  update(id: number | string, input: AnnouncementInput): Promise<Announcement> {
    return http.put<Announcement>(`/announcements/${id}`, input)
  },

  /** お知らせを削除する */
  remove(id: number | string): Promise<void> {
    return http.delete<void>(`/announcements/${id}`)
  },
}
