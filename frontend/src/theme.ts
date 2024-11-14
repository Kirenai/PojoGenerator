import {
  createSystem,
  defaultConfig,
  defineConfig,
  mergeConfigs,
} from '@chakra-ui/react'

const customConfig = defineConfig({
  theme: {
    tokens: {
      fonts: {},
    },
    semanticTokens: {
      colors: {
        bg: {
          DEFAULT: {
            value: { _light: '{colors.gray.50}', _dark: '{colors.gray.900}' },
          },
        },
      },
    },
  },
})

const merge = mergeConfigs(defaultConfig, customConfig)
export const system = createSystem(merge)
