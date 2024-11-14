import { Payload } from '../../domain/model/payload'
import { PojoGeneratorRepositoryPort } from '../../domain/port/pojoGeneratorPort'

export const createPojoGeneratorRepositoryAdapterMock =
  (): PojoGeneratorRepositoryPort => {
    return {
      generate: async (payload: Payload): Promise<string> => {
        return 'aaaa'
      },
    }
  }
