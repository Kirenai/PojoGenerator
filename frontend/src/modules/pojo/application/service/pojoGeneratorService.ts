import { Payload } from '../../domain/model/payload'
import { PojoGeneratorRepositoryPort } from '../../domain/port/pojoGeneratorPort'

export const createPojoGeneratorService = (
  pojoGeneratorPort: PojoGeneratorRepositoryPort,
) => {
  return {
    generate: async (payload: Payload) => {
      return pojoGeneratorPort.generate(payload)
    },
  }
}
