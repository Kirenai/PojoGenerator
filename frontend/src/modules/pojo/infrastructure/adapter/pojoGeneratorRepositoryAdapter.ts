import { Payload } from '../../domain/model/payload'
import { PojoGeneratorRepositoryPort } from '../../domain/port/pojoGeneratorPort'
import instance from '../config/axiosConfig'

export const createPojoGeneratorRepositoryAdapter =
  (): PojoGeneratorRepositoryPort => {
    return {
      generate: async (payload: Payload): Promise<string> => {
        const response = await instance.post('/generate', payload)
        return window.URL.createObjectURL(response.data)
      },
    }
  }
