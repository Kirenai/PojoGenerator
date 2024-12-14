import { Box, Code, Container, HStack, Text, VStack } from '@chakra-ui/react'
import PojoForm from './components/PojoForm'

const PojoHome = () => {
  return (
    <Container p="4">
      <VStack gap="0.5" py="2">
        <Box w="auto" fontSize={{ lg: 'lg' }}>
          <HStack justifyContent="space-between">
            <Text>Objeto de entrada: </Text>
            <Code fontSize="md">person.id - I</Code>
          </HStack>
          <HStack justifyContent="space-between">
            <Text>Array de salida: </Text>
            <Code fontSize="md">data[n].person.id - O</Code>
          </HStack>
        </Box>
      </VStack>
      <PojoForm />
    </Container>
  )
}

export default PojoHome
