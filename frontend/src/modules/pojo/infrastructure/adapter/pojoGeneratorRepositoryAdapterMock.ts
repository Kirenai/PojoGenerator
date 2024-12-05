import { PojoGeneratorRepositoryPort } from '../../domain/port/pojoGeneratorPort'

export const createPojoGeneratorRepositoryAdapterMock =
  (): PojoGeneratorRepositoryPort => {
    return {
      generate: async (): Promise<string> => {
        return 'aaaa'
      },
    }
  }
